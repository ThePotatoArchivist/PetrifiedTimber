package archives.tater.petrifiedtimber.client.render;

import archives.tater.petrifiedtimber.mixin.client.StandingSignRendererAccessor;

import com.mojang.math.Axis;
import com.mojang.math.Transformation;
import net.minecraft.client.renderer.blockentity.StandingSignRenderer;
import net.minecraft.client.renderer.blockentity.WallAndGroundTransformations;
import net.minecraft.client.renderer.blockentity.state.SignRenderState;
import net.minecraft.world.level.block.PlainSignBlock;
import net.minecraft.world.level.block.state.properties.RotationSegment;

import org.joml.Matrix4f;

/**
 * @see StandingSignRenderer
 */
public class BigSignRendering {

    public static final WallAndGroundTransformations<SignRenderState.SignTransformations> PETRIFIED_SIGN_TRANSFORMATIONS = new WallAndGroundTransformations<>(
            direction -> createTransformations(PlainSignBlock.Attachment.WALL, direction.toYRot()),
            segment -> createTransformations(PlainSignBlock.Attachment.GROUND, RotationSegment.convertToDegrees(segment)),
            16
    );

    private static final float RENDER_SCALE = 0.6666667F;
    private static final float RENDER_UPSCALE = 1 / RENDER_SCALE;

    private static SignRenderState.SignTransformations createTransformations(PlainSignBlock.Attachment attachmentType, float angle) {
        return new SignRenderState.SignTransformations(
                bodyTransformation(attachmentType, angle),
                textTransformation(attachmentType, angle, true),
                textTransformation(attachmentType, angle, false)
        );
    }

    private static Transformation bodyTransformation(PlainSignBlock.Attachment attachmentType, float angle) {
        return new Transformation(baseTransformation(angle, attachmentType).scale(RENDER_SCALE, -RENDER_SCALE, -RENDER_SCALE));
    }

    private static Transformation textTransformation(PlainSignBlock.Attachment attachmentType, float angle, boolean isFrontText) {
        var result = baseTransformation(angle, attachmentType);
        if (!isFrontText)
            result.rotate(Axis.YP.rotationDegrees(180));

        var s = 0.010416667F;
        return new Transformation(result.translate(StandingSignRendererAccessor.getTEXT_OFFSET()).scale(s, -s, s));
    }

    private static Matrix4f baseTransformation(float angle, PlainSignBlock.Attachment attachmentType) {
        var result = new Matrix4f();

        if (attachmentType == PlainSignBlock.Attachment.WALL) result
                .translate(0.5f, 0.5f, 0.5f)
                .rotate(Axis.YP.rotationDegrees(-angle))
                .translate(0, 0, -0.4375f)
                .scale(RENDER_UPSCALE)
                .translate(0, -0.3125f, 0);
        else result
                .translate(0.5f, 0, 0.5f)
                .scale(RENDER_UPSCALE)
                .translate(0, 0.5f, 0)
                .rotate(Axis.YP.rotationDegrees(-angle));

        return result;
    }
}
