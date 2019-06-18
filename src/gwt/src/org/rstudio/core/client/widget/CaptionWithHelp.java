/*
 * CaptionWithHelp.java
 *
 * Copyright (C) 2009-19 by RStudio, Inc.
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
package org.rstudio.core.client.widget;

import com.google.gwt.user.client.ui.Widget;
import org.rstudio.core.client.a11y.A11y;
import org.rstudio.core.client.resources.ImageResource2x;
import org.rstudio.core.client.theme.res.ThemeResources;
import org.rstudio.studio.client.RStudioGinjector;
import org.rstudio.studio.client.common.GlobalDisplay;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

public class CaptionWithHelp extends Composite
{
   public CaptionWithHelp(String caption, String helpCaption)
   {
      this(caption, helpCaption, null);
   }
   
   public CaptionWithHelp(String caption, 
                          String helpCaption,
                          final String rstudioLinkName)
   {
      RStudioGinjector.INSTANCE.injectMembers(this);
      
      rstudioLinkName_ = rstudioLinkName;
      
      HorizontalPanel panel = new HorizontalPanel();
      panel.setWidth("100%");
      captionLabel_ = new FormLabel(caption);
      panel.add(captionLabel_);
      helpPanel_ = new HorizontalPanel();
      Image helpImage = new Image(new ImageResource2x(ThemeResources.INSTANCE.help2x()));
      helpImage.setStylePrimaryName(styles.helpImage());
      A11y.setDecorativeImage(helpImage.getElement());
      helpPanel_.add(helpImage);
      HyperlinkLabel link = new HyperlinkLabel(helpCaption);
      link.addStyleName(styles.helpLink());
      link.addClickHandler(new ClickHandler() {
         public void onClick(ClickEvent event)
         {
            if (rstudioLinkName_ != null)
               globalDisplay_.openRStudioLink(rstudioLinkName_,
                                              includeVersionInfo_);
         }  
      });
      helpPanel_.add(link);
      panel.add(helpPanel_);
      panel.setCellHorizontalAlignment(helpPanel_, 
                                       HasHorizontalAlignment.ALIGN_RIGHT);
          
      outerPanel_ = panel;
      initWidget(panel);
   }
   
   public void setCaption(String caption)
   {
      captionLabel_.setText(caption);
   }

   /**
    * Associate caption with a widget for a11y
    * @param widget
    */
   public void setFor(Widget widget)
   {
      captionLabel_.setFor(widget);
   }
   
   public void setRStudioLinkName(String linkName)
   {
      rstudioLinkName_ = linkName;
   }
   
   public void setIncludeVersionInfo(boolean include)
   {
      includeVersionInfo_ = include;
   }
   
   public void setHelpVisible(boolean visible)
   {
      helpPanel_.setVisible(visible);
   }
   
   public void setStyleName(String style)
   {
      outerPanel_.setStyleName(style);
   }
   
   @Inject
   void initialize(GlobalDisplay globalDisplay)
   {
      globalDisplay_ = globalDisplay;
   }
   
   static interface Resources extends ClientBundle
   {
      @Source("CaptionWithHelp.css")
      Styles styles();
   }

   static interface Styles extends CssResource
   {
      String helpImage();
      String helpLink();
   }

   private static Styles styles = GWT.<Resources>create(Resources.class).styles();

   public static void ensureStylesInjected()
   {
      styles.ensureInjected();
   }
   
   private FormLabel captionLabel_;
   private String rstudioLinkName_;
   private boolean includeVersionInfo_ = true;
   private HorizontalPanel helpPanel_;
   private HorizontalPanel outerPanel_;
   private GlobalDisplay globalDisplay_;
}
