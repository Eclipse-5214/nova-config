#version 330 core
out vec4 FragColor;
in vec2 TexCoords;

uniform vec4 color1; // Pink
uniform vec4 color2; // Mauve
uniform vec4 color3; // Sapphire
uniform float scale; // Controls stripe width

void main() {
    float pattern = mod(TexCoords.x + TexCoords.y, scale) / scale; // Diagonal stripes

    // Ensure each color gets an equal portion
    if (pattern < 0.33) {
        FragColor = mix(color1, color2, smoothstep(0.0, 0.33, pattern)); // Pink to Mauve
    } else if (pattern < 0.66) {
        FragColor = mix(color2, color3, smoothstep(0.33, 0.66, pattern)); // Mauve to Sapphire
    } else {
        FragColor = mix(color3, color1, smoothstep(0.66, 1.0, pattern)); // Sapphire to Pink
    }
}
