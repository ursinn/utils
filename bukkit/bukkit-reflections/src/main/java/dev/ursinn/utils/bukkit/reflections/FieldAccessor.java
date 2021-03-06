/*
 * MIT License
 *
 * Copyright (c) 2021 Ursin Filli
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.ursinn.utils.bukkit.reflections;

/**
 * An interface for retrieving the field content.
 *
 * @param <T> field type
 * @author Ursin Filli
 * @version 1.0
 * @since 1.0
 */
public interface FieldAccessor<T> {

    /**
     * Retrieve the content of a field.
     *
     * @param target the target object, or NULL for a static field
     * @return the value of the field
     */
    T get(Object target);

    /**
     * Set the content of a field.
     *
     * @param target the target object, or NULL for a static field
     * @param value  the new value of the field
     */
    void set(Object target, Object value);

    /**
     * Determine if the given object has this field.
     *
     * @param target the object to test
     * @return TRUE if it does, FALSE otherwise
     */
    boolean hasField(Object target);
}