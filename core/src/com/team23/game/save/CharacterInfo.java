package com.team23.game.save;

import com.team23.game.utils.Position;

import java.util.ArrayList;

public class CharacterInfo {
    public Position position;
    public ArrayList<AbilityInfo> abilities;
    public CharacterInfo(){
        this.position = new Position();
        this.abilities = new ArrayList<>();
    }
}