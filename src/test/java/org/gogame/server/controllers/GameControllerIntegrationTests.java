package org.gogame.server.controllers;

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
        UserInviteDto inviteA = TestData.InviteDtoUtils.createA(userRepo);
        String inviteJson = objectMapper.writeValueAsString(inviteA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/game/invite/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inviteJson)
        ).andExpect(
                MockMvcResultMatchers.status().is(HttpStatus.CREATED.value())
        );
    }

}
