/*
 * Adapted from The MIT License (MIT)
 *
 * Copyright (c) 2016-2020 DaPorkchop_
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 *
 * Any persons and/or organizations using this software must include the above copyright notice and this permission notice,
 * provide sufficient credit to the original authors of the project (IE: DaPorkchop_), as well as provide a link to the original project.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package net.daporkchop.pepsimod.util.config.impl;

import com.google.gson.JsonObject;
import net.daporkchop.pepsimod.util.config.IConfigTranslator;

public class BedBomberTranslator implements IConfigTranslator {
    public static final BedBomberTranslator INSTANCE = new BedBomberTranslator();
    public float range = 4.0f;
    public int delay = 500;
    public boolean resupply = true;

    private BedBomberTranslator() {

    }

    public void encode(JsonObject json) {
        json.addProperty("range", this.range);
        json.addProperty("delay", this.delay);
        json.addProperty("resupply", this.resupply);
    }

    public void decode(String fieldName, JsonObject json) {
        this.range = this.getFloat(json, "range", this.range);
        this.delay = this.getInt(json, "delay", this.delay);
        this.resupply = this.getBoolean(json, "resupply", this.resupply);
    }

    public String name() {
        return "bedbomber";
    }
}
