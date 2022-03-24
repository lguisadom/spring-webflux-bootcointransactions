package com.nttdata.lagm.bootcointx.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "wallet")
public class Wallet {
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	private String identification; // DNI, CEX, Pasaporte
	private String cellphone;
	private String email;
	private BigDecimal amount;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime date;
}
