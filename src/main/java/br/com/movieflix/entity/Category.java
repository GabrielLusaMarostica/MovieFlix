package br.com.movieflix.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter //getter lombok
@Setter //setter lombok
@Entity //definir entidade no banco de dados
@Table(name = "category") //definir que é uma tabela no banco de dados
public class Category {

    @Id //definir que é um id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // definir a geração automatica do id
    private Long id;

    @Column(length = 100, nullable = false) //definir que é uma coluna do banco de dados, com tamanho
    // maximo de 100 letras e nao pode ser nulo
    private String name;

}
