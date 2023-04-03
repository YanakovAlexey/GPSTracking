package com.example.demo.backend.viewModel;

import com.example.demo.backend.domain.PersistentObject;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserViewModel extends PersistentObject {
    String username;
    String email;
    String phone;
    String password;
    Integer roleId;
}
