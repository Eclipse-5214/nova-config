package co.stellarskys.novaconfig.utils

import kotlinx.io.IOException
import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.ModContainer
import org.lwjgl.opengl.GL20
import java.nio.file.Files
import java.nio.file.Path

import java.util.function.Consumer

class Shader(vertexPath: String, fragmentPath: String) {
    private var programId = 0

    init {

        val vertexShader = compileShader(GL20.GL_VERTEX_SHADER, readFile(vertexPath))
        val fragmentShader = compileShader(GL20.GL_FRAGMENT_SHADER, readFile(fragmentPath))

        programId = GL20.glCreateProgram()
        GL20.glAttachShader(programId, vertexShader)
        GL20.glAttachShader(programId, fragmentShader)
        GL20.glLinkProgram(programId)

        // Error checking
        if (GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == GL20.GL_FALSE) {
            throw RuntimeException("Shader linking failed: " + GL20.glGetProgramInfoLog(programId))
        }

        GL20.glDeleteShader(vertexShader)
        GL20.glDeleteShader(fragmentShader)
    }

    fun bind() {
        GL20.glUseProgram(programId)
    }

    fun unbind() {
        GL20.glUseProgram(0)
    }

    fun setUniform(name: String, vararg values: Float) {
        val location = GL20.glGetUniformLocation(programId, name)
        when (values.size) {
            1 -> GL20.glUniform1f(location, values[0])
            2 -> GL20.glUniform2f(location, values[0], values[1])
            3 -> GL20.glUniform3f(location, values[0], values[1], values[2])
            4 -> GL20.glUniform4f(location, values[0], values[1], values[2], values[3])
            else -> throw IllegalArgumentException("Unsupported uniform size!")
        }
    }

    private fun readFile(path: String): String {
        val modPath: Path = FabricLoader.getInstance().getModContainer("nova-config")
            .flatMap { it.findPath("assets/nova-config/shaders/$path") }
            .orElseThrow { IOException("Shader file not found!") }
        val shaderSource = Files.readString(modPath)
        return shaderSource
    }

    private fun compileShader(type: Int, source: String): Int {
        val shaderId = GL20.glCreateShader(type)
        GL20.glShaderSource(shaderId, source)
        GL20.glCompileShader(shaderId)

        // Check for compilation errors
        if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == GL20.GL_FALSE) {
            throw RuntimeException("Shader compilation failed: " + GL20.glGetShaderInfoLog(shaderId))
        }

        return shaderId
    }
}