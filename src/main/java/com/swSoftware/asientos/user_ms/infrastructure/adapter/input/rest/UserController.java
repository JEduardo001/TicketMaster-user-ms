package com.swSoftware.asientos.user_ms.infrastructure.adapter.input.rest;

import com.swSoftware.asientos.user_ms.application.dto.responseApi.DtoResponseApi;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUserRegister;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUserUpdate;
import com.swSoftware.asientos.user_ms.application.usecase.user.GetAllUsersUseCase;
import com.swSoftware.asientos.user_ms.application.usecase.user.GetUserUseCase;
import com.swSoftware.asientos.user_ms.application.usecase.user.RegisterUserUseCase;
import com.swSoftware.asientos.user_ms.application.usecase.user.UpdateUserUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<DtoResponseApi> createUser(@Valid @RequestBody DtoUserRegister request){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .message("User registred")
                .data(registerUserCase.execute(request))
                .build()
        );
    }

    /*
    @PostMapping("/login")
    public ResponseEntity<DtoResponseApiLogIn> login(@Valid @RequestBody DtoLogin request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(),data.getPassword()));
        String token = jwtService.createToken(authentication.getName());

        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApiLogIn.builder()
                .status(HttpStatus.OK.value())
                .message("logged")
                .token(token)
                .build()
        );
    }*/


    @PatchMapping("/{idUser}")
    public ResponseEntity<DtoResponseApi> updateUserUseCase(@PathVariable UUID idUser, @Valid @RequestBody DtoUserUpdate request){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("User updated")
                .data(updateUserUseCase.execute(idUser,request))
                .build()
        );
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<DtoResponseApi> getUser(@PathVariable UUID idUser){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("User obtained")
                .data(getUserUseCase.execute(idUser))
                .build()
        );
    }

    @GetMapping
    public ResponseEntity<DtoResponseApi> getAllUser(
            @RequestParam(required = false) UUID lastId,
            @RequestParam(defaultValue = "15") int limit
    ) {
        return ResponseEntity.ok(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Users obtained")
                .data(getAllUsersUseCase.execute(lastId, limit))
                .build());
    }

}
