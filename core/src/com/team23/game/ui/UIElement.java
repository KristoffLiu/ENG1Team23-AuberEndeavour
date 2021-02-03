package com.team23.game.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.utils.Null;
import com.team23.game.actors.CustomActor;

/***
 * the actor showing the user-interface elements only.
 */
public class UIElement extends CustomActor implements IUIElement{
    UIElement uiParent = null;
    UIPage rootPage = null;
    HorizontalAlignment horizontalAlignment = HorizontalAlignment.leftAlignment;
    VerticalAlignment verticalAlignment = VerticalAlignment.topAlignment;
    float relativeX = 0;
    float relativeY = 0;

    /***
     * add UI element
     */
    public UIElement() {
        super();
    }

    /***
     * set the UI Parent
     * @param _uiParent parent of the ui
     */
    public void setUIParent(UIElement _uiParent){
        this.uiParent = _uiParent;
    }

    /***
     * set the UI Parent
     * @return return the parent of this ui
     */
    public UIElement getUIParent() {
        return uiParent;
    }

    @Override
    public void setRootPage(UIPage rootPage) {
        if(this.rootPage != rootPage){
            this.rootPage = rootPage;
            rootPage.addUIElement(this);
        }
    }

    @Override
    public UIPage getRootPage() {
        return this.rootPage;
    }

    /***
     * set the UI Parent
     * @return get the relative coordination of X
     */
    @Override
    public float getRelativeX() {
        return relativeX;
    }

    /***
     * set the UI Parent
     * @return get the relative coordination of X
     */
    @Override
    public float getRelativeY() {
        return relativeY;
    }

    /***
     * set the relative coordination of X
     * @param relativeX the relative coordination of X
     */
    public void setRelativeX(float relativeX){
        if(this.rootPage != null){
            float x_UIParent = 0;
            float width_UIParent = 0;
            float offset = 0f;
            float parentWidth = this.getRootPage().getWidth();

            if(uiParent == null){

            }
            else{
                x_UIParent = uiParent.getX();
                width_UIParent = uiParent.getWidth();
                offset += x_UIParent;
                parentWidth = this.getParent().getWidth();
            }

            switch (this.horizontalAlignment){
                case leftAlignment:
                    this.setX(offset + relativeX);
                    break;
                case centreAlignment:
                    offset += parentWidth / 2 - this.getWidth() * this.getScaleX() / 2;
                    this.setX(offset + relativeX);
                    break;
                case rightAlignment:
                    offset += parentWidth - this.getWidth() * this.getScaleX();
                    this.setX(offset - relativeX);
                    break;
            }
        }
        else{
            setX(relativeX);
        }
        this.relativeX = relativeX;
    }

    /***
     * set the relative coordination of Y
     * @param relativeY the relative coordination of Y
     */
    public void setRelativeY(float relativeY){
        if(this.rootPage != null){
            float y_UIParent = 0;
            float height_UIParent = 0;
            float offset = 0f;
            float parentHeight = 0f;
            parentHeight = this.getRootPage().getHeight();

            if(uiParent == null){

            }
            else{
                y_UIParent = uiParent.getY() + uiParent.getHeight();
                height_UIParent = uiParent.getHeight();
                offset += y_UIParent;
                parentHeight = this.getParent().getHeight();
            }

            switch (this.verticalAlignment) {
                case topAlignment:
                    offset += (parentHeight - this.getHeight() * this.getScaleY());
                    this.setY(offset - relativeY);
                    break;
                case centreAlignment:
                    offset += parentHeight / 2 - (this.getHeight() * this.getScaleY()) / 2;
                    this.setY(offset + relativeY);
                    break;
                case bottomAlignment:
                    this.setY(offset + relativeY);
                    break;
            }
        }
        else{
            setX(relativeY);
        }
        this.relativeY = relativeY;
    }

    /***
     * get the HorizontalAlignment
     * @return get the HorizontalAlignment
     */
    @Override
    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /***
     * get the HorizontalAlignment
     * @return get the HorizontalAlignment
     */
    @Override
    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    /***
     * set the horizontalAlignment
     * @param alignment set the horizontal alignment
     */
    public void setHorizontalAlignment(HorizontalAlignment alignment){
        horizontalAlignment = alignment;
        setRelativeX(relativeX);
    }

    /***
     * set the horizontalAlignment
     * @param alignment set the vertiacal alignment
     */
    public void setVerticalAlignment(VerticalAlignment alignment){
        verticalAlignment = alignment;
        setRelativeY(relativeY);
    }

    /***
     * set the set relative position
     * @param relativeX set the relative coordination of X
     * @param relativeY set the relative coordination of Y
     */
    public void setRelativePosition(float relativeX, float relativeY) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        setHorizontalAlignment(this.horizontalAlignment);
        setVerticalAlignment(this.verticalAlignment);
    }

    /***
     * set the set relative position
     * @param relativeX set the relative coordination of X
     * @param relativeY set the relative coordination of Y
     * @param horizontalAlignment set the horizontal alignment
     * @param verticalAlignment set the vertical alignment
     */
    @Override
    public void setRelativePosition(float relativeX, float relativeY, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        setHorizontalAlignment(horizontalAlignment);
        setVerticalAlignment(verticalAlignment);
    }

    /***
     * set size of the ui element
     * @param width set the width
     * @param height set the height
     */
    public void setSize(float width, float height){
        this.setWidth(width);
        this.setHeight(height);
    }

    public enum HorizontalAlignment{
        leftAlignment, centreAlignment, rightAlignment
    }

    public enum VerticalAlignment{
        topAlignment, centreAlignment, bottomAlignment
    }

    float animationOrigin_X = 0f;
    float animationOrigin_Y = 0f;

    /***
     * set the animation
     * @param x set the x coordination for animation
     * @param y set the x coordination for animation
     */
    public void setAnimationOrigin(float x, float y){
        animationOrigin_X = x;
        animationOrigin_Y = y;
    }

    /***
     * hide this ui element immediately
     */
    public void hide(){
        AlphaAction uiElementAlphaAction = Actions.alpha(0f,0f);
        VisibleAction uiElementVisibleAction = Actions.visible(false);
        this.addAction(uiElementAlphaAction);
        this.addAction(uiElementVisibleAction);
    }

    /***
     * hide this ui element in a duration of time
     * @param duration set the x coordination for animation
     */
    public void hide(float duration){
        AlphaAction uiElementAlphaAction = Actions.alpha(0,duration);
        VisibleAction uiElementVisibleAction = Actions.visible(false);
        DelayAction uiElementDelayAction = Actions.delay(duration,uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
        this.addAction(uiElementDelayAction);
    }

    /***
     * appear this ui element immediately
     */
    public void appear(){
        VisibleAction uiElementVisibleAction = Actions.visible(true);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f,0f);
        this.addAction(uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
    }

    /***
     * appear this ui element in a duration of time
     * @param duration duration of appearing
     */
    public void appear(float duration){
        VisibleAction uiElementVisibleAction = Actions.visible(true);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f,duration);
        this.addAction(uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
    }

    /***
     * fade out this ui element in a duration of time
     * @param duration duration of fading
     */
    public void fadeOut(float duration){
        fadeOut(0f, 0f, duration, null);
    }

    /***
     * fade out this ui element in a duration of time
     */
    public void fadeOut(float offset, boolean isHorizontalShift, float duration){
        if(isHorizontalShift) fadeOut(offset, 0f, duration, null); else fadeOut(0f, offset, duration, null);
    }

    /***
     * fade out this ui element in a duration of time
     */
    public void fadeOut(float offset_x, float offset_y, float duration){
        fadeOut(offset_x, offset_y, duration, null);
    }

    /***
     * fade out this ui element in a duration of time
     */
    public void fadeOut(float offset_x, float offset_y, float duration, @Null Interpolation interpolation){
        if(this.isVisible()){
            AlphaAction uiElementAlphaAction = Actions.alpha(0f, duration, interpolation);
            MoveByAction uiElementMoveByAction = Actions.moveBy(offset_x, offset_y, duration, interpolation);
            MoveByAction endingMoveByAction = Actions.moveBy(-offset_x,-offset_y,0f);
            ParallelAction parallelAction = Actions.parallel(uiElementAlphaAction, uiElementMoveByAction);
            SequenceAction sequenceAction = Actions.sequence(parallelAction,endingMoveByAction);
            this.addAction(sequenceAction);
        }
    }

    /***
     * fade out this ui element in a duration of time
     */
    public void fadeOut(float x, float y, float offset_x, float offset_y, float duration, @Null Interpolation interpolation){
        if(this.isVisible()){
            MoveToAction uiElementMoveToAction = Actions.moveTo(x,y,0f);
            AlphaAction uiElementAlphaAction = Actions.alpha(0f, duration, interpolation);
            MoveByAction uiElementMoveByAction = Actions.moveBy(offset_x, offset_y, duration, interpolation);
            ParallelAction parallelAction = Actions.parallel(uiElementAlphaAction, uiElementMoveByAction);
            SequenceAction sequenceAction = Actions.sequence(uiElementMoveToAction,parallelAction);
            this.addAction(sequenceAction);
        }
    }

    /***
     * fade in this ui element in a duration of time
     */
    public void fadeIn(float duration){
        fadeIn(0f, 0f, duration, null);
    }

    /***
     * fade in this ui element in a duration of time
     */
    public void fadeIn(float value, boolean isHorizontalShift, float duration){
        if(isHorizontalShift) fadeIn(value, 0f, duration, null); else fadeIn(0f, value, duration, null);

    }

    /***
     * fade in this ui element in a duration of time
     */
    public void fadeIn(float offset_x, float offset_y, float duration){
        fadeIn(offset_x, offset_y, duration, null);
    }

    /***
     * fade in this ui element in a duration of time
     */
    public void fadeIn(float offset_x, float offset_y, float duration, @Null Interpolation interpolation){
        if(this.isVisible()){
            VisibleAction uiElementVisibleAction = Actions.visible(true);
            MoveByAction beginningMoveToAction = Actions.moveBy(-offset_x, -offset_y,0f);
            AlphaAction beginningAlphaAction = Actions.alpha(0f, 0);
            AlphaAction uiElementAlphaAction = Actions.alpha(1f, duration, interpolation);
            MoveByAction uiElementMoveByAction = Actions.moveBy(offset_x, offset_y, duration, interpolation);
            ParallelAction parallelAction = Actions.parallel(uiElementAlphaAction, uiElementMoveByAction);
            SequenceAction sequenceAction = Actions.sequence(uiElementVisibleAction,beginningMoveToAction,beginningAlphaAction,parallelAction);
            this.addAction(sequenceAction);
        }
    }

    /***
     * fade in this ui element in a duration of time
     */
    public void fadeIn(float x, float y, float offset_x, float offset_y, float duration, @Null Interpolation interpolation){
        if(this.isVisible()){
            VisibleAction uiElementVisibleAction = Actions.visible(true);
            MoveToAction uiElementMoveToAction = Actions.moveTo(x - offset_x,y - offset_y,0f);
            AlphaAction beginningAlphaAction = Actions.alpha(0f, 0);
            AlphaAction uiElementAlphaAction = Actions.alpha(1f, duration, interpolation);
            MoveByAction uiElementMoveByAction = Actions.moveBy(offset_x, offset_y, duration, interpolation);
            ParallelAction parallelAction = Actions.parallel(uiElementAlphaAction, uiElementMoveByAction);
            SequenceAction sequenceAction = Actions.sequence(uiElementVisibleAction,uiElementMoveToAction,beginningAlphaAction,parallelAction);
            this.addAction(sequenceAction);
        }
    }

    @Override
    public void act(float delta){
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        setHorizontalAlignment(horizontalAlignment);
        setVerticalAlignment(verticalAlignment);
    }
}
