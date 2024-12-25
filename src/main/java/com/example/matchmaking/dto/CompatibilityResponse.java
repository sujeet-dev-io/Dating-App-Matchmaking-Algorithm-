package com.example.matchmaking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompatibilityResponse {

    private String user1Id;
    private String user2Id;
    private double compatibilityScore;
}
