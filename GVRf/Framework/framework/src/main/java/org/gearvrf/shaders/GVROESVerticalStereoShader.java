/* Copyright 2015 Samsung Electronics Co., LTD
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gearvrf.shaders;

import android.content.Context;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRShader;
import org.gearvrf.GVRShaderData;
import org.gearvrf.utility.TextFile;
import org.gearvrf.R;

/**
 * Shader which samples from either the top or bottom half of an external texture.
 * This shader does not use light sources.
 * @<code>
 *    a_position    position vertex attribute
 *    a_texcoord    texture coordinate vertex attribute
 *    u_color       color to modulate texture
 *    u_opacity     opacity
 *    u_right       1 = right eye, 0 = left eye
 *    u_texture     external texture
 * </code>
 */
public class GVROESVerticalStereoShader extends GVRShader
{
    public GVROESVerticalStereoShader(GVRContext gvrContext)
    {
        super("float3 u_color float u_opacity ",
              "samplerExternalOES u_texture",
              "float3 a_position float2 a_texcoord", GLSLESVersion.VULKAN);
        Context context = gvrContext.getContext();
        setSegment("FragmentTemplate", TextFile.readTextFile(context, R.raw.oes_vertical_frag));
        setSegment("VertexTemplate", TextFile.readTextFile(context, R.raw.pos_tex_ubo));
    }

    protected void setMaterialDefaults(GVRShaderData material)
    {
        material.setVec3("u_color", 1, 1, 1);
        material.setFloat("u_opacity", 1);
    }
}
