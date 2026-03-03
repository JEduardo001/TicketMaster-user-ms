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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User Management", description = "Endpoints for user authentication, registration, and profile management")
public class UserController {

    private final RegisterUserUseCase registerUserCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final AuthenticationManager authenticationManager;
    private final IUserService iUserService;
    private final JwtService jwtService;

    @Operation(summary = "Register a new user", description = "Creates a new user account in the system")
    @ApiResponse(responseCode = "201", description = "User successfully registered")
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

    @Operation(summary = "User login", description = "Authenticates user credentials and returns a JWT token")
    @ApiResponse(responseCode = "200", description = "Authentication successful")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
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

    @Operation(summary = "Update user", description = "Updates profile information for an existing user")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @PatchMapping("/{idUser}")
    public ResponseEntity<DtoResponseApi> updateUserUseCase(
            @Parameter(description = "UUID of the user") @PathVariable UUID idUser,
            @Valid @RequestBody DtoUserUpdate request){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("User updated")
                .data(updateUserUseCase.execute(idUser,request))
                .build()
        );
    }

    @Operation(summary = "Get user by ID", description = "Retrieves specific user details using their UUID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "User data retrieved")
    @GetMapping("/{idUser}")
    public ResponseEntity<DtoResponseApi> getUser(@Parameter(description = "UUID of the user") @PathVariable UUID idUser){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("User obtained")
                .data(getUserUseCase.execute(idUser))
                .build()
        );
    }

    @Operation(summary = "List all users", description = "Returns a paginated list of all registered users")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Users list retrieved")
    @GetMapping
    public ResponseEntity<DtoResponseApi> getAllUser(
            @Parameter(description = "UUID cursor for pagination") @RequestParam(required = false) UUID lastId,
            @Parameter(description = "Page size limit") @RequestParam(defaultValue = "15") int limit
    ) {
        return ResponseEntity.ok(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("Users obtained")
                .data(getAllUsersUseCase.execute(lastId, limit))
                .build());
    }

    @Operation(summary = "Verify user for reservation", description = "Internal validation to check if a user is eligible to reserve a seat")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Verification successful")
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