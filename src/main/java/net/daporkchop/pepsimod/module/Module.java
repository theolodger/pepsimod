/*
 * Adapted from the Wizardry License
 *
 * Copyright (c) 2016-2019 DaPorkchop_
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

package net.daporkchop.pepsimod.module;

import net.daporkchop.pepsimod.util.event.impl.Event;

/**
 * The root of most pepsimod utilities are modules. These are individual, toggleable utilities with a distinct function that
 * <p>
 * i need to come up with a definition for this
 * although in all likelihood i'll forget and this useless javadoc will still be sitting here in three years
 *
 * @author DaPorkchop_
 */
public interface Module extends Event {
    /**
     * Called when this module is enabled.
     * <p>
     * Any events registered by this module will be registered automatically before this method is called.
     */
    default void enabled() {
    }

    /**
     * Called when this module is disabled.
     * <p>
     * Any events registered by this module will be deregistered automatically before this method is called.
     */
    default void disabled() {
    }
}
