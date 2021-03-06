/*
 * PanmirrorUIContext.java
 *
 * Copyright (C) 2020 by RStudio, PBC
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */

package org.rstudio.studio.client.panmirror.ui;


import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsType;

@JsType
public class PanmirrorUIContext
{
   public Getter getDefaultResourceDir;
   public Mapper mapPathToResource;
   public Mapper mapResourceToURL;
   public Mapper translateText;

   
   @JsFunction 
   public interface Getter
   {
      String get();
   }
   
   @JsFunction
   public interface Mapper
   {
      String map(String path);
   }
}


