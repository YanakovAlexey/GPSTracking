package com.example.demo.backend.domain;

import com.example.demo.models.MessageType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "message")
@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message extends PersistentObject{

    @Column(name = "type")
    MessageType type;
    @Column(name = "header")
    String header;
    @Column(name = "body")
    String body;
}
