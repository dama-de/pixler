package de.damarus.drawing.data;

import android.graphics.Bitmap;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Composition {

    private int width = -1;
    private int height = -1;

    private int activeLayerIndex = -1;

    private List<Bitmap> layers = new ArrayList<>();

    private Composition() {
    }

    public static Composition createComposition(int w, int h) {
        Composition c = new Composition();
        c.width = w;
        c.height = h;
        c.addLayer();

        return c;
    }

    public Bitmap getLayer(int layerIndex) {
        return layers.get(layerIndex);
    }

    public void addLayer() {
        Bitmap newLayer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        layers.add(++activeLayerIndex, newLayer);
    }


    public void removeLayer() {
        if (layers.size() <= 1) throw new IllegalStateException("Can't remove last layer");

        layers.remove(activeLayerIndex);
        activeLayerIndex = Math.min(activeLayerIndex, layers.size() - 1);
    }


    public void setActiveLayer(int newActiveLayer) {
        Preconditions.checkPositionIndex(newActiveLayer, layers.size(), "layer index");

        this.activeLayerIndex = newActiveLayer;
    }

    public Bitmap getActiveLayer() {
        return layers.get(activeLayerIndex);
    }

    public int getActiveLayerIndex() {
        return activeLayerIndex;
    }

    public List<Bitmap> getLayers() {
        return Collections.unmodifiableList(layers);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
