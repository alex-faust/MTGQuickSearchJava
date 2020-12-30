package com.magic.mtgquicksearchjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Card
{
    @SerializedName("name")
    private String name;

    @SerializedName("uri")
    private String uri;

    @SerializedName("image_uris")
    private Map<String, String> image_uris;

    /*public Card(String name, String uri, List<String> image_uris)
    {
        this.name = name;
        this.uri = uri;
        this.image_uris = image_uris;
    }*/

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public String getNormalImage_uris()
    {
        return image_uris.get("normal");
    }

   /*public void setNormalImage_uris(Map<String, String> normalsImage_uris)
    {
        this.normalImage_uris = image_uris;
    }*/
}
