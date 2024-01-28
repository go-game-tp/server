package org.gogame.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gogame.server.domain.entities.dto.UserInviteDto;
import org.gogame.server.domain.entities.dto.UserLoginDto;
import org.gogame.server.domain.entities.dto.UserRegisterDto;
import org.gogame.server.repositories.TestData;
import org.gogame.server.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class GameControllerIntegrationTests {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final UserRepository userRepo;

    @Autowired
    public GameControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, UserRepository userRepo) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.userRepo = userRepo;
    }

    @Test
    public void testThatGameInviteCanBeSent() throws Exception {

        UserRegisterDto regA = TestData.RegisterDtoUtils.createA();
        MvcResult mvcAResult = ControllerUtils.registerUser(mockMvc, objectMapper, regA);

        UserRegisterDto regB = TestData.RegisterDtoUtils.createB();
        MvcResult mvcBResult = ControllerUtils.registerUser(mockMvc, objectMapper, regB);


        UserInviteDto invite = UserInviteDto.builder()
                .userSenderId(ControllerUtils.getUserId(mvcAResult))
                .userReceiverId(ControllerUtils.getUserId(mvcBResult))
                .build();

        sendGameInvite(
                invite,
                ControllerUtils.getJwtToken(mvcAResult)
        );
    }

    @Test
    public void testThatGameInviteCanBeAccepted() throws Exception {

        UserRegisterDto regA = TestData.RegisterDtoUtils.createA();
        MvcResult mvcAResult = ControllerUtils.registerUser(mockMvc, objectMapper, regA);

        UserRegisterDto regB = TestData.RegisterDtoUtils.createB();
        MvcResult mvcBResult = ControllerUtils.registerUser(mockMvc, objectMapper, regB);

        var senderId = ControllerUtils.getUserId(mvcAResult);
        var receiverId = ControllerUtils.getUserId(mvcBResult);

        UserInviteDto invite = UserInviteDto.builder()
                .userSenderId(senderId)
                .userReceiverId(receiverId)
                .build();

        sendGameInvite(
                invite,
                ControllerUtils.getJwtToken(mvcAResult)
        );

        String inviteJson = objectMapper.writeValueAsString(invite);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/game/invite/accept")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inviteJson)
                        .header("Authorization", ControllerUtils.getJwtToken(mvcBResult))
        ).andExpect(
                MockMvcResultMatchers.status().is(HttpStatus.OK.value())
        );
    }

    private MvcResult sendGameInvite(UserInviteDto userInviteDto, String senderToken) throws Exception {
        String inviteJson = objectMapper.writeValueAsString(userInviteDto);

        return mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/game/invite/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inviteJson)
                        .header("Authorization", senderToken)
        ).andExpect(
                MockMvcResultMatchers.status().is(HttpStatus.CREATED.value())
        ).andReturn();
    }

}
