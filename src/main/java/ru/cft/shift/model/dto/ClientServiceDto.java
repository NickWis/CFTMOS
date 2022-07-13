package ru.cft.shift.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cft.shift.model.ClientService;
import ru.cft.shift.model.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientServiceDto {

    private Long id;
    private String description;
    private Date date;
    private String city;
    private Long userId;

    public static ClientServiceDto from(ClientService clientService){
        return ClientServiceDto.builder()
                .id(clientService.getId())
                .description(clientService.getDescription())
                .date(clientService.getDate())
                .city(clientService.getCity())
                .userId(clientService.getClient().getId())
                .build();
    }

    public static List<ClientServiceDto> from(List<ClientService> clientServices){
        return clientServices.stream().map(ClientServiceDto::from).collect(Collectors.toList());
    }
}
