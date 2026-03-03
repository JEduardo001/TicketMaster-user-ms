package com.swSoftware.asientos.user_ms.infrastructure.adapter.input.rest;

import com.swSoftware.asientos.user_ms.application.dto.auth.DtoLogin;
import com.swSoftware.asientos.user_ms.application.dto.responseApi.DtoResponseApi;
import com.swSoftware.asientos.user_ms.application.dto.responseApi.DtoResponseApiLogIn;
import com.swSoftware.asientos.user_ms.application.dto.seatReserve.DtoSeatReserve;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUserRegister;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUserUpdate;
import com.swSoftware.asientos.user_ms.application.usecase.user.GetAllUsersUseCase;
import com.swSoftware.asientos.user_ms.application.usecase.user.GetUserUseCase;
import com.swSoftware.asientos.user_ms.application.usecase.user.RegisterUserUseCase;
import com.swSoftware.asientos.user_ms.application.usecase.user.UpdateUserUseCase;
import com.swSoftware.asientos.user_ms.domain.port.IUserService;
import com.swSoftware.asientos.user_ms.domain.service.user.GeneralUserService;
import com.swSoftware.asientos.user_ms.infrastructure.config.service.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.swSoftware.asientos.user_ms.domain.common.HeaderConstants.CORRELATION_KEY;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final AuthenticationManager authenticationManager;
    private final IUserService iUserService;
    private final JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<DtoResponseApi> createUser(@Valid @RequestBody DtoUserRegister request){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .message("User registred")
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .data(registerUserCase.execute(request))
                .build()
        );
    }


    @PostMapping("/login")
    public ResponseEntity<DtoResponseApiLogIn> login(@Valid @RequestBody DtoLogin request){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        List<String> roles = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = jwtService.createToken(request.username(), roles);

        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApiLogIn.builder()
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .status(HttpStatus.OK.value())
                .token(token)
                .build()
        );
    }

    @PatchMapping("/{idUser}")
    public ResponseEntity<DtoResponseApi> updateUserUseCase(@PathVariable UUID idUser, @Valid @RequestBody DtoUserUpdate request){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("User updated")
                .data(updateUserUseCase.execute(idUser,request))
                .build()
        );
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<DtoResponseApi> getUser(@PathVariable UUID idUser){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
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
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("Users obtained")
                .data(getAllUsersUseCase.execute(lastId, limit))
                .build());
    }

    @PostMapping("/seat")
    public ResponseEntity<DtoResponseApi> verifyUserToReserveSeat(@Valid @RequestBody DtoSeatReserve request){
        iUserService.verifyUserToReserveSeat(request);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("Seat being booked")
                .build()
        );
    }

}
