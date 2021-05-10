package com.itacademy.example.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPetIdsDto {

    private int userId;
    private int petId;
}
