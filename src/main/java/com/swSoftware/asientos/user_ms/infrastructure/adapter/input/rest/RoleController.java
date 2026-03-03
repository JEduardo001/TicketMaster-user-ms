package com.swSoftware.asientos.user_ms.infrastructure.adapter.input.rest;

import com.swSoftware.asientos.user_ms.application.dto.responseApi.DtoResponseApi;
import com.swSoftware.asientos.user_ms.application.dto.role.DtoCreateRole;
import com.swSoftware.asientos.user_ms.application.dto.role.DtoRoleUpdate;
import com.swSoftware.asientos.user_ms.application.usecase.role.CreateRoleUseCase;
import com.swSoftware.asientos.user_ms.application.usecase.role.GetAllRolesUseCase;
import com.swSoftware.asientos.user_ms.application.usecase.role.GetRoleUseCase;
import com.swSoftware.asientos.user_ms.application.usecase.role.UpdateRoleUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.swSoftware.asientos.user_ms.domain.common.HeaderConstants.CORRELATION_KEY;

@RestController
@RequestMapping("/api/v1/role")
@AllArgsConstructor
@Tag(name = "Role Management", description = "Endpoints for managing system security roles")
public class RoleController {

    private final GetAllRolesUseCase getAllRolesUseCase;
    private final CreateRoleUseCase createRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final GetRoleUseCase getRoleUseCase;

    @Operation(summary = "Create a new role", description = "Registers a new security role in the system")
    @ApiResponse(responseCode = "201", description = "Role created successfully")
    @PostMapping()
    public ResponseEntity<DtoResponseApi> createRole(@Valid @RequestBody DtoCreateRole request){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("Role created")
                .data(createRoleUseCase.execute(request))
                .build()
        );
    }

    @Operation(summary = "Partial update of a role", description = "Updates specific fields of an existing role by its ID")
    @ApiResponse(responseCode = "200", description = "Role updated successfully")
    @PatchMapping("/{idRole}")
    public ResponseEntity<DtoResponseApi> updateRole(
            @Parameter(description = "ID of the role to update", example = "1") @PathVariable Long idRole,
            @Valid @RequestBody DtoRoleUpdate request){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("Role updated")
                .data(updateRoleUseCase.execute(idRole,request))
                .build()
        );
    }

    @Operation(summary = "Get role by ID", description = "Retrieves detailed information of a specific role")
    @ApiResponse(responseCode = "200", description = "Role found")
    @GetMapping("/{idRole}")
    public ResponseEntity<DtoResponseApi> getUser(
            @Parameter(description = "ID of the role to retrieve", example = "1") @PathVariable Long idRole){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("Role obtained")
                .data(getRoleUseCase.execute(idRole))
                .build()
        );
    }

    @Operation(summary = "Get all roles with pagination", description = "Retrieves a paginated list of roles using cursor-based pagination")
    @ApiResponse(responseCode = "200", description = "List of roles retrieved")
    @GetMapping
    public ResponseEntity<DtoResponseApi> getAllRole(
            @Parameter(description = "Last role ID from the previous page for cursor pagination", example = "10") @RequestParam(required = false) Long lastId,
            @Parameter(description = "Number of records per page", example = "15") @RequestParam(defaultValue = "15") int limit
    ) {
        return ResponseEntity.ok(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Roles obtained")
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .data(getAllRolesUseCase.execute(lastId, limit))
                .build());
    }
}
