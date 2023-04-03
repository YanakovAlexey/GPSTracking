package com.example.demo.backend.viewModel;


import com.example.demo.backend.domain.PersistentObject;
import lombok.*;

import javax.persistence.metamodel.Type;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageViewModel extends PersistentObject {
    Type typeOfSms;
    String header;
    String body;
}
