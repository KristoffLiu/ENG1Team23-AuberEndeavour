package com.team23.game.save;

import com.team23.game.actors.characters.Infiltrator;
import com.team23.game.utils.Position;

/***
 * Save Object for the Enemy
 */
public class EnemyInfo extends CharacterInfo {
    public int power;
    public EnemyInfo(){
    }

    public EnemyInfo(Infiltrator infiltrator){
        this.position = Position.fromVector2(infiltrator.getPosition());
        this.moveSpeed = infiltrator.getMovSpeed();
        this.power = infiltrator.getPower();
    }
}
