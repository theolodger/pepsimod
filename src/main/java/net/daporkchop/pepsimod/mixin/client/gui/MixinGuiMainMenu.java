/*
 * Adapted from the Wizardry License
 *
 * Copyright (c) 2017-2018 DaPorkchop_
 *
 * Permission is hereby granted to any persons and/or organizations using this software to copy, modify, merge, publish, and distribute it.
 * Said persons and/or organizations are not allowed to use the software or any derivatives of the work for commercial use or any other means to generate income, nor are they allowed to claim this software as their own.
 *
 * The persons and/or organizations are also disallowed from sub-licensing and/or trademarking this software without explicit permission from DaPorkchop_.
 *
 * Any persons and/or organizations using this software must disclose their source code and have it publicly available, include this license, provide sufficient credit to the original author of the project (IE: DaPorkchop_), as well as provide a link to the original project.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package net.daporkchop.pepsimod.mixin.client.gui;

import net.daporkchop.pepsimod.module.ModuleManager;
import net.daporkchop.pepsimod.module.api.Module;
import net.daporkchop.pepsimod.util.ImageUtils;
import net.daporkchop.pepsimod.util.PepsiUtils;
import net.daporkchop.pepsimod.util.Texture;
import net.daporkchop.pepsimod.util.colors.ColorizedText;
import net.daporkchop.pepsimod.util.colors.rainbow.RainbowText;
import net.daporkchop.pepsimod.util.config.impl.GeneralTranslator;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

import static net.daporkchop.pepsimod.util.misc.Default.pepsiMod;

@Mixin(GuiMainMenu.class)
public abstract class MixinGuiMainMenu extends GuiScreen {
    public final ColorizedText PEPSIMOD_TEXT_GRADIENT = PepsiUtils.getGradientFromStringThroughColor("PepsiMod 11.0 for Minecraft " + MinecraftForge.MC_VERSION, new Color(255, 0, 0), new Color(0, 0, 255), new Color(255, 255, 255));
    public final ColorizedText PEPSIMOD_AUTHOR_GRADIENT = new RainbowText("Made by DaPorkchop_");
    @Shadow
    private String splashText;
    private Texture TITLE;

    @Inject(method = "initGui", at = @At("RETURN"))
    public void addPepsiIconAndChangeSplash(CallbackInfo ci) {
        pepsiMod.isInitialized = true;
        if (!pepsiMod.hasInitializedModules) {
            for (Module module : ModuleManager.AVALIBLE_MODULES) {
                module.doInit();
            }
            PepsiUtils.setBlockIdFields();
            pepsiMod.hasInitializedModules = true;
        }
        TITLE = new Texture(ImageUtils.imgs.get(1));
        this.splashText = PepsiUtils.COLOR_ESCAPE + "c" + PepsiUtils.COLOR_ESCAPE + "lpepsi" + PepsiUtils.COLOR_ESCAPE + "9" + PepsiUtils.COLOR_ESCAPE + "lmod";
        ModuleManager.sortModules(GeneralTranslator.INSTANCE.sortType);
    }

    @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V", ordinal = 0))
    public void removeMenuLogoInit(TextureManager textureManager, ResourceLocation resource) {
        int titleX = width / 2 - 150, titleY = 20;
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        TITLE.render(titleX, titleY + 10, 300, 100);
    }

    @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMainMenu;drawTexturedModalRect(IIIIII)V"))
    public void removeMenuLogoRendering(GuiMainMenu guiMainMenu, int x, int y, int textureX, int textureY, int width, int height) {
    }

    @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMainMenu;drawModalRectWithCustomSizedTexture(IIFFIIFF)V"))
    public void removeSubLogoRendering(int x, int y, float a, float b, int c, int d, float e, float f) {
    }

    @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMainMenu;drawString(Lnet/minecraft/client/gui/FontRenderer;Ljava/lang/String;III)V"))
    public void removeAllDrawStrings(GuiMainMenu guiMainMenu, FontRenderer fontRenderer1, String string, int i1, int i2, int i3) {
    }

    @Inject(method = "drawScreen", at = @At("RETURN"))
    public void addDrawPepsiStuff(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        this.drawString(this.fontRenderer, PepsiUtils.COLOR_ESCAPE + "cCopyright Mojang AB. Do not distribute!", this.width - this.fontRenderer.getStringWidth("Copyright Mojang AB. Do not distribute!") - 2, this.height - 10, -1);
        PEPSIMOD_TEXT_GRADIENT.drawAtPos(this, 2, this.height - 20);
        PEPSIMOD_AUTHOR_GRADIENT.drawAtPos(this, 2, this.height - 10);
    }
}
