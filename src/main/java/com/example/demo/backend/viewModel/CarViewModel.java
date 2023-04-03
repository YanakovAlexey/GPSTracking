package com.example.demo.backend.viewModel;

import com.example.demo.backend.domain.PersistentObject;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarViewModel extends PersistentObject {
    String brand;
    String model;
    String registrationNumber;

}
