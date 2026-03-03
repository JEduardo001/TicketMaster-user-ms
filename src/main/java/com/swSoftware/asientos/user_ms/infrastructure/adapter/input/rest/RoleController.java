package com.swSoftware.asientos.user_ms.infrastructure.adapter.input.rest;

import com.swSoftware.asientos.user_ms.application.dto.responseApi.DtoResponseApi;
import com.swSoftware.asientos.user_ms.application.dto.role.DtoCreateRole;
import com.swSoftware.asientos.user_ms.application.dto.role.DtoRoleUpdate;
import com.swSoftware.asientos.user_ms.application.usecase.role.CreateRoleUseCase;
import com.swSoftware.asientos.user_ms.application.usecase.role.GetAllRolesUseCase;
import com.swSoftware.asientos.user_ms.application.usecase.role.GetRoleUseCase;
import com.swSoftware.asientos.user_ms.application.usecase.role.UpdateRoleUseCase;
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
public class RoleController {

    private final GetAllRolesUseCase getAllRolesUseCase;
    private final CreateRoleUseCase createRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final GetRoleUseCase getRoleUseCase;

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

    @PatchMapping("/{idRole}")
    public ResponseEntity<DtoResponseApi> updateRole(@PathVariable Long idRole, @Valid @RequestBody DtoRoleUpdate request){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("Role updated")
                .data(updateRoleUseCase.execute(idRole,request))
                .build()
        );
    }

    @GetMapping("/{idRole}")
    public ResponseEntity<DtoResponseApi> getUser(@PathVariable Long idRole){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("Role obtained")
                .data(getRoleUseCase.execute(idRole))
                .build()
        );
    }

    @GetMapping
    public ResponseEntity<DtoResponseApi> getAllRole(
            @RequestParam(required = false) Long lastId,
            @RequestParam(defaultValue = "15") int limit
    ) {
        return ResponseEntity.ok(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Roles obtained")
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .data(getAllRolesUseCase.execute(lastId, limit))
                .build());
    }

}
