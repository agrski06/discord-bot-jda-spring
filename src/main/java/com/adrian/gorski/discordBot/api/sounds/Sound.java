package com.adrian.gorski.discordBot.api.sounds;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sound")
@RequiredArgsConstructor
@Data
public class Sound {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String link;

}
