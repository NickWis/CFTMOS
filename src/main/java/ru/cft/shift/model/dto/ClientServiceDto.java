package ru.cft.shift.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cft.shift.model.ClientService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientServiceDto {

    @Schema(description = "ID услуги", example = "1")
    private Long id;
    @Schema(description = "Описание услуги", example = "ВОТ ЭТО ОПИСАНИЕ!")
    private String description;
    @Schema(description = "Дата создания услуги", example = "2022-04-05T19:58:01.000+00:00")
    private Date date;
    @Schema(description = "Город оказания услуги", example = "Novosibirsk")
    private String city;
    @Schema(description = "ID пользователя, который оказывает услугу", example = "1")
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
