package inhatc.cse.inhive.role.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import inhatc.cse.inhive.response.Response;
import inhatc.cse.inhive.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<Response<List<String>>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/by-code")
    public ResponseEntity<Response<String>> getRoleByCode(@RequestParam("code") String code) {
        return ResponseEntity.ok(roleService.getRoleByCode(code));
    }

    @GetMapping("/by-name")
    public ResponseEntity<Response<String>> getRoleByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(roleService.getRoleByName(name));
    }
}
