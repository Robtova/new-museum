package com.robtova.modern;

import java.util.ArrayList;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

public class Tesselator {
	
	private ArrayList<Vector3f> verts = new ArrayList<Vector3f>();
	private ArrayList<Vector2f> tex = new ArrayList<Vector2f>();
	private ArrayList<Float> norms = new ArrayList<Float>(), cols = new ArrayList<Float>();
	
	private TesselatorRender render;
	
	public Tesselator() {
		this(null);
	}
	
	public Tesselator(TesselatorRender render) {
		this.render = render;
	}
	
	public void setRender(TesselatorRender render) {
		this.render = render;
	}

	Vector3f vec = new Vector3f(0f, 0f, 0f), normals = new Vector3f();
	private boolean mat_changes;
	private float yaw, pitch;
	
	public void translate(float x, float y, float z) {
		vec.add(x, y, z);
		this.mat_changes = true;
	}
	
	public void rotate(float pitch, float yaw) {
		this.pitch = pitch;
		this.yaw = yaw;
		this.mat_changes = true;
	}
	
	public void clear_vec() {
		vec.set(0, 0, 0);
		this.pitch = 0;
		this.yaw = 0;
		mat_changes = false;
	}

	public void addVert(float x, float y, float z, float u, float v, float nx, float ny, float nz) {
		this.addVert(x, y, z, u, v, nx, ny, nz, 1f, 1f, 1f, 1f);
	}
	
	public void addVert(float x, float y, float z, float u, float v, float nx, float ny, float nz, float[] col) {
		this.addVert(x, y, z, u, v, nx, ny, nz, col[0], col[1], col[2], col[3]);
	}
	
	public void addVert(float x, float y, float z, float u, float v, float nx, float ny, float nz, float r, float g, float b, float a) {
		if(this.mat_changes) {
			float zp = z * (float) Math.cos(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch)) + vec.z;
	        float xp =  x * (float) (Math.sin(Math.toRadians(yaw))) * (float) Math.cos(Math.toRadians(pitch)) + vec.x;
	        float yp =  y * (float) Math.sin(Math.toRadians(pitch)) + vec.y;
	        
	        verts.add(new Vector3f(xp, yp, zp));
	        
	        zp = nz * (float) Math.cos(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
	        xp =  nx * (float) (Math.sin(Math.toRadians(yaw))) * (float) Math.cos(Math.toRadians(pitch));
	        yp =  ny * (float) Math.sin(Math.toRadians(pitch));
	        
			tex.add(new Vector2f(u, 1f - v));
			norms.add(xp);
			norms.add(yp);
			norms.add(zp);
			cols.add(r);
			cols.add(g);
			cols.add(b);
			cols.add(a);
		} else {
			verts.add(new Vector3f(x, y, z));
			tex.add(new Vector2f(u, 1f - v));
			norms.add(nx);
			norms.add(ny);
			norms.add(nz);
			cols.add(r);
			cols.add(g);
			cols.add(b);
			cols.add(a);
		}
	}
	
	public Mesh create() {
		Mesh m = new Mesh();

		if(this.render != null) this.render.tesselate(this);
		
        int [] indexes = new int[verts.size() / 4 * 6];
        int o = 0;
        
        for(int i = 0; i < indexes.length; i+=6) {
        	indexes[i + 0] = (o);
        	indexes[i + 1] = (o + 1);
        	indexes[i + 2] = (o + 2);
        	indexes[i + 3] = (o + 2);
        	indexes[i + 4] = (o + 3);
        	indexes[i + 5] = (o);
        	o += 4;
        }
        
        float[] ns = new float[norms.size()];
        for(int i = 0; i < norms.size(); i++) {
        	ns[i] = norms.get(i);
        }
        
        Vector3f[] vs = new Vector3f[verts.size()];
        for(int i = 0; i < verts.size(); i++) {
        	vs[i] = verts.get(i);
        }
        
        Vector2f[] ts = new Vector2f[tex.size()];
        for(int i = 0; i < tex.size(); i++) {
        	ts[i] = tex.get(i);
        }
        
        float[] col = new float[cols.size()];
        for(int i = 0; i < cols.size(); i++) {
        	col[i] = cols.get(i);
        }

        // Setting buffers
        m.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vs));
        m.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(ts));
        m.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(ns));
        m.setBuffer(Type.Index, 1, BufferUtils.createIntBuffer(indexes));
        m.setBuffer(Type.Color, 4, BufferUtils.createFloatBuffer(col));
        m.updateBound();
        
        return m;
	}
}
