package io.anuke.mindustry.entities.bullet;

import io.anuke.arc.entities.Effects;
import io.anuke.arc.entities.Effects.Effect;
import io.anuke.mindustry.content.StatusEffects;
import io.anuke.mindustry.content.fx.BulletFx;
import io.anuke.mindustry.content.fx.Fx;
import io.anuke.mindustry.game.Content;
import io.anuke.mindustry.type.ContentType;
import io.anuke.mindustry.type.StatusEffect;
import io.anuke.mindustry.world.Tile;

public abstract class BulletType extends Content{
    public float lifetime;
    public float speed;
    public float damage;
    public float hitSize = 4;
    public float drawSize = 40f;
    public float drag = 0f;
    public boolean pierce;
    public Effect hitEffect, despawnEffect;

    /**Effect created when shooting.*/
    public Effect shootEffect = Fx.none;
    /**Extra smoke effect created when shooting.*/
    public Effect smokeEffect = Fx.none;

    public float splashDamage = 0f;
    /**Knockback in velocity.*/
    public float knockback;
    /**Whether this bullet hits tiles.*/
    public boolean hitTiles = true;
    /**Status effect applied on hit.*/
    public StatusEffect status = StatusEffects.none;
    /**Intensity of applied status effect in terms of duration.*/
    public float statusIntensity = 0.5f;
    /**What fraction of armor is pierced, 0-1*/
    public float armorPierce = 0f;
    /**Whether to sync this bullet to clients.*/
    public boolean syncable;
    /**Whether this bullet type collides with tiles.*/
    public boolean collidesTiles = true;
    /**Whether this bullet type collides with tiles that are of the same team.*/
    public boolean collidesTeam = false;
    /**Whether this bullet type collides with air units.*/
    public boolean collidesAir = true;
    /**Whether this bullet types collides with anything at all.*/
    public boolean collides = true;
    /**Whether velocity is inherited from the shooter.*/
    public boolean keepVelocity = true;

    public BulletType(float speed, float damage){
        this.speed = speed;
        this.damage = damage;
        lifetime = 40f;
        hitEffect = BulletFx.hitBulletSmall;
        despawnEffect = BulletFx.hitBulletSmall;
    }

    public boolean collides(Bullet bullet, Tile tile){
        return true;
    }

    public void hitTile(Bullet b, Tile tile){
        hit(b);
    }

    public void hit(Bullet b){
        hit(b, b.x, b.y);
    }

    public void hit(Bullet b, float hitx, float hity){
        Effects.effect(hitEffect, hitx, hity, b.rot());
    }

    public void despawned(Bullet b){
        Effects.effect(despawnEffect, b.x, b.y, b.rot());
    }

    public void draw(Bullet b){
    }

    public void init(Bullet b){
    }

    public void update(Bullet b){
    }

    @Override
    public ContentType getContentType(){
        return ContentType.bullet;
    }
}
