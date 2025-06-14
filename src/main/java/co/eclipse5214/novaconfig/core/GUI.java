package co.eclipse5214.novaconfig.core;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

public class GUI extends Screen {
    public GUI(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        ButtonWidget buttonWidget = ButtonWidget.builder(Text.of("Hello World"), (btn) -> {
            // make something happen
            this.client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.of("Cool!"), Text.of("Hi from the toast"))
            );
        }).dimensions(40,40,120,20).build();

        this.addDrawableChild(buttonWidget);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        context.drawText(this.textRenderer, "Special Button", 40, 40 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
    }

}
