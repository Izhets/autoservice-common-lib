package ru.redcollar.autoservice.model.dto;

import lombok.*;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDto {

    @NonNull
    private Long id;
    private Date dateRequest;
    private String status;
    private Date dateCompletion;
    private Long clientId;
    private Long employeeId;
}
