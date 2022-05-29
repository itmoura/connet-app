package com.connet.app.model.entity;

import com.connet.app.model.dto.ProcessDTO;
import com.connet.app.model.enumeration.Status;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Process implements Serializable {

    private static final long serialVersionUID = 6480432909995901463L;

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "process_id")
    private UUID processId;

    @Column(name ="installer_id")
    private Long installerId;

    @Column(name ="client_id")
    private UUID clientId;

    @Column(name ="status")
    private Status status;

    @Column(name ="final_price")
    private String finalPrice;

    @Column(name ="final_distance")
    private String finalDistance;

    @Column(name ="data")
    private LocalDateTime date;

    @Column(name ="plan_id")
    private Long planId;

    @Column(name ="rating")
    private Float rating;

    public static Process convert(ProcessDTO processDTO) {
        Process process = new Process();
        BeanUtils.copyProperties(processDTO, process);
        return process;
    }
}
