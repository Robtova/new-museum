package com.robtova.modern;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import jme3tools.optimize.GeometryBatchFactory;
import jme3tools.optimize.LodGenerator;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.MeshCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.LodControl;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.terrain.geomipmap.TerrainGridLodControl;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.lodcalc.DistanceLodCalculator;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.robtova.modern.level.Level;
import com.robtova.modern.level.collision.NodeCollision;
import com.robtova.modern.level.collision.StandCollision;
import com.robtova.modern.level.collision.WorldCollision;
import com.robtova.modern.screen.ScreenControl;
import com.robtova.modern.screen.plaques.PlaqueInExForm;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class Program extends SimpleApplication implements ScreenController {

    public static Input input;
    
    public Player player;
    public BulletAppState bulletAppState;
    
    Tesselator tesselator;
    public Level level;
    
    FilterPostProcessor fpp;
    SSAOFilter ssaoFilter;
    FogFilter fog;
    
    public static ScreenControl end = new ScreenControl("end");
    private Nifty nifty;
    public ScreenControl screen_control = end;
    
    DirectionalLight sun, sun1, sun2, sun3;
    
    public ArrayList<NodeCollision> node_collision = new ArrayList<NodeCollision>();

    public static void main(final String[] args) {
        Program app = new Program();
        app.setShowSettings(true);
        app.setPauseOnLostFocus(true);
        AppSettings settings = new AppSettings(true);
        settings.put("Width", 1280);
        settings.put("Height", 720);
        settings.put("Title", "TateModern");
        settings.put("VSync", false);

        settings.put("Samples", 0);
        
        try {
        	settings.setIcons(new BufferedImage[]{
        			ImageIO.read(app.getClass().getResource("/misc/icon256.png")),
        			ImageIO.read(app.getClass().getResource("/misc/icon128.png")),
        			ImageIO.read(app.getClass().getResource("/misc/icon64.png")),
        			ImageIO.read(app.getClass().getResource("/misc/icon32.png")),
        			ImageIO.read(app.getClass().getResource("/misc/icon16.png"))
        	});
        } catch (IOException e) {
        	e.printStackTrace();
        }

        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
    	this.flyCam.setMoveSpeed(50f);
    	
        ScreenshotAppState state = new ScreenshotAppState();
        this.stateManager.attach(state);

        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);

        this.viewPort.setBackgroundColor(new ColorRGBA(0.8f, 0.9f, 1f, 1f));
        
        ResourceFiles.load(assetManager);
        
        try {
        	level = new Level(32, 16, 32, (BufferedImage) ImageIO.read(this.getClass().getResource("/level.png")));
			tesselator = new Tesselator(level);
			
			Mesh m = tesselator.create();
			Geometry geo = new Geometry("Level", m); 
			Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
			
			/* Textures */
			mat.setTexture("DiffuseMap", ResourceFiles.blocks);
			mat.setTexture("NormalMap", ResourceFiles.block_normals);
			
			/* Matrix */
			mat.setBoolean("UseAlpha",true);
			mat.setBoolean("UseMaterialColors", true);   
			mat.setColor("Ambient", ColorRGBA.White);
		    mat.setColor("Diffuse", ColorRGBA.White);
		    mat.setColor("Specular", ColorRGBA.White);
		    mat.setFloat("Shininess", 0f);
		    mat.setBoolean("UseVertexColor", true);
		    mat.setBoolean("VertexLighting", true);
		    mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		    mat.getAdditionalRenderState().setFaceCullMode(FaceCullMode.Back);
		    
			geo.setMaterial(mat);
			geo.setQueueBucket(Bucket.Transparent);
			MeshCollisionShape mcs = new MeshCollisionShape(m);
			geo.addControl(new RigidBodyControl(mcs, 0.0f));
			
			LodGenerator lod = new LodGenerator(geo);
			lod.bakeLods(LodGenerator.TriangleReductionMethod.PROPORTIONAL,0.5f);
			LodControl lc = new LodControl();
			geo.addControl(lc);
			
			rootNode.attachChild(geo);
			bulletAppState.getPhysicsSpace().add(geo);
			
			System.out.println("Mesh Loaded");
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        this.addSculpture("in_ex-form.obj", new Vector3f(9.5f, 1f, 6f), new Vector3f(1f, 1f, 1f), new Quaternion().fromAngleAxis((float) Math.PI / 2f, Vector3f.UNIT_Y), 
        		ColorRGBA.White, new ColorRGBA(0.7f, 0.8f, 1f, 1.0f).mult(12f), ColorRGBA.White, 0f);
        this.addSculpture("in_ex-form_base.obj", new Vector3f(9.5f, 1f, 6f), new Vector3f(1f, 1f, 1f), null, 
        		ResourceFiles.base, null, ColorRGBA.White, ColorRGBA.White, ColorRGBA.White, 0f);
        node_collision.add(new StandCollision(new PlaqueInExForm(), this.addSculpture("in_ex-form_stand", "stand.obj", new Vector3f(9.5f, 1f, 7f), 
        		new Vector3f(1f, 1f, 1f), new Quaternion().fromAngleAxis((float) Math.PI / 2f, Vector3f.UNIT_Y), ResourceFiles.loadImage(assetManager, "in_ex-form_stand.png"), 
        		null, ColorRGBA.White, ColorRGBA.White, ColorRGBA.White, 0f)));
        this.addSculpture("ball-plane-and-hole.obj", new Vector3f(15.32f, 1f, 1.365f), new Vector3f(1f, 1f, 1f), new Quaternion().fromAngleAxis((float) Math.PI / 2f, Vector3f.UNIT_Y),  
        		ColorRGBA.White, ColorRGBA.White.mult(6f), ColorRGBA.White, 128f);
        this.addSculpture("plane_base.obj", new Vector3f(15.32f, 1f, 1.365f), new Vector3f(1f, 1f, 1f), new Quaternion().fromAngleAxis((float) Math.PI / 2f, Vector3f.UNIT_Y),  
        		ResourceFiles.base, null, ColorRGBA.White, ColorRGBA.White, ColorRGBA.White, 128f);
        		
        input = new Input(this);
        player = new Player(this);
        player.update(this, 1);
        
        initFilter(false);
        
        this.getCamera().setFrustumPerspective(45f, (float) this.settings.getWidth() / (float) this.settings.getHeight(), 0.1f, 100f);
        this.initKeys();
        this.loadScreen();
    }

    public Node addSculpture(String name, Vector3f position, Vector3f scale, Quaternion rotation, Texture texture, Texture normals, ColorRGBA ambient, ColorRGBA diffuse, ColorRGBA specular, float shine) {
    	return addSculpture(name.substring(0, name.lastIndexOf('.')) + "Node", name, position, scale, rotation, texture, normals, ambient, diffuse, specular, shine);
    }
    
    public Node addSculpture(String node, String name, Vector3f position, Vector3f scale, Quaternion rotation, Texture texture, Texture normals, ColorRGBA ambient, ColorRGBA diffuse, ColorRGBA specular, float shine) {
    	/* Load Model */
    	Spatial mesh = assetManager.loadModel("models/" + name);
		Node model = new Node(node);
		model.attachChild(mesh);
		
		/* Load Material */
		Material material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md"); 
		material.setTexture("DiffuseMap", texture);
		if(normals != null) material.setTexture("NormalMap", normals);
		material.setBoolean("UseAlpha",true);
		material.setBoolean("UseMaterialColors", true);   
		material.setColor("Ambient", ambient);
	    material.setColor("Diffuse", diffuse);
	    material.setColor("Specular", specular);
	    material.setFloat("Shininess", shine);
	    material.setFloat("AlphaDiscardThreshold", 0.5f);
	    material.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
	    model.setQueueBucket(Bucket.Transparent);
	    model.setMaterial(material);
	    
	    /* Load Physics */
	    RigidBodyControl bod = new RigidBodyControl(0f);
		model.addControl(bod);
		bod.setPhysicsLocation(position);
		
		LodGenerator lod = new LodGenerator((Geometry) model.getChild(0));
		lod.bakeLods(LodGenerator.TriangleReductionMethod.PROPORTIONAL, 0.25f, 0.5f);
		LodControl lc = new LodControl();
		((Geometry) model.getChild(0)).addControl(lc);
		
		if(rotation != null) bod.setPhysicsRotation(rotation);
		model.setLocalScale(scale);
		rootNode.attachChild(model);
		bulletAppState.getPhysicsSpace().add(model);

		return model;
    }

    public Node addSculpture(String name, Vector3f position, Vector3f scale, Quaternion rotation, ColorRGBA ambient, ColorRGBA diffuse, ColorRGBA specular, float shine) {
    	Texture normals = null;
    	try {
    		normals = ResourceFiles.loadImage(assetManager, name.substring(0, name.lastIndexOf('.')) + "_normals.png");
    	} catch (Exception e) {
    		System.out.println(name.substring(0, name.lastIndexOf('.')) + "_normals.png does not exist.");
    	}
    	return addSculpture(name, position, scale, rotation, ResourceFiles.loadImage(assetManager, name.substring(0, name.lastIndexOf('.')) + ".png"), normals, ambient, diffuse, specular, shine);
    }
    
    public void initFilter(boolean g) {
    	sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1f, -1, 0f).normalizeLocal());
        sun.setColor(new ColorRGBA(1f, 0.96f, 0.91f, 1f).mult(0.1f));
        if(g) rootNode.addLight(sun);
        
        sun1 = new DirectionalLight();
        sun1.setDirection(new Vector3f(-1f, -1, 0f).normalizeLocal());
        sun1.setColor(new ColorRGBA(1f, 0.96f, 0.91f, 1f).mult(0.05f));
        if(g) rootNode.addLight(sun1);
        
        sun2 = new DirectionalLight();
        sun2.setDirection(new Vector3f(0f, -1, 1f).normalizeLocal());
        sun2.setColor(new ColorRGBA(1f, 0.96f, 0.91f, 1f).mult(0.025f));
        if(g) rootNode.addLight(sun2);
        
        sun3 = new DirectionalLight();
        sun3.setDirection(new Vector3f(0.5f, -1, -0.5f).normalizeLocal());
        sun3.setColor(new ColorRGBA(1f, 0.96f, 0.91f, 1f).mult(0.1f));
        if(!g) rootNode.addLight(sun3);

        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.9f));
        rootNode.addLight(al);
        
        fpp = new FilterPostProcessor(assetManager);
        ssaoFilter = new SSAOFilter(3f, 1f, 0.2f, 0.1f);
        //fpp.addFilter(ssaoFilter);

        fog = new FogFilter();
        fog.setFogColor(new ColorRGBA(1f, 1f, 1f, 1f));
        fog.setFogDistance(400f);
        fog.setFogDensity(1.0f);
        
        if(!g) viewPort.addProcessor(fpp);
    }

    private void initKeys() {
    	input.addKey("Lefts", KeyInput.KEY_A);
        input.addKey("Rights", KeyInput.KEY_D);
        input.addKey("Ups", KeyInput.KEY_W);
        input.addKey("Downs", KeyInput.KEY_S);
        //input.addKey("Jumps", KeyInput.KEY_SPACE);
        this.inputManager.deleteMapping(SimpleApplication.INPUT_MAPPING_EXIT);
        input.addKey("Esc", KeyInput.KEY_ESCAPE);
        input.addKey("ToggleRender", KeyInput.KEY_F2);
        input.addMouse("Left", MouseInput.BUTTON_LEFT);
        input.addKey("Fog", KeyInput.KEY_F);
    }

    @Override
    public void simpleUpdate(final float tpf) {
    	float delta = 1; 
    	player.update(this, delta);
        if(input.getKeyWasDown("ToggleRender")) {
        	if(viewPort.getProcessors().contains(fpp)) {
        		viewPort.removeProcessor(fpp);
        		rootNode.removeLight(sun3);
        		rootNode.addLight(sun);
        		rootNode.addLight(sun1);
        		rootNode.addLight(sun2);
        	} else {
        		viewPort.addProcessor(fpp);
        		rootNode.removeLight(sun);
        		rootNode.removeLight(sun1);
        		rootNode.removeLight(sun2);
        		rootNode.addLight(sun3);
        	}
        }
        if(input.getKeyWasDown("Fog")) {
        	if(fpp.getFilterList().contains(fog)) {
        		fpp.removeFilter(fog);
        	} else {
        		fpp.addFilter(fog);
        	}
        }
        if(input.getKeyPressed("Left")) this.getWorldIntersection();
        
        input.update();
    }
    
    public void addScreen(ScreenControl screen) {
    	if(this.screen_control == screen) return;
    	System.out.println("Add " + screen.getId() + ".");
    	screen.onLoad(nifty, nifty.getScreen(screen.getId()));
    	nifty.gotoScreen(screen.getId());
    	this.screen_control = screen;
    	if(screen.getId().equals("end")) {
        	getInputManager().setCursorVisible(false);
            flyCam.setEnabled(true);
        } else {
        	getInputManager().setCursorVisible(true);
        	flyCam.setEnabled(false);
        }
    }
    
    public void loadScreen() {
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        nifty = niftyDisplay.getNifty();
        nifty.fromXml("interface/plaque.xml", "start", this);

        guiViewPort.addProcessor(niftyDisplay);
        
        addScreen(new ScreenControl("start"));
    }
    
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("bind(" + screen.getScreenId() + ")");
    }

    public void onStartScreen() {
    	screen_control.onLoad(nifty, nifty.getScreen(screen_control.getId()));
    }

    public void onEndScreen() {
    }

    public void quit() {
        addScreen(end);
        getInputManager().setCursorVisible(false);
        flyCam.setEnabled(true);
    }
    
    private BitmapText c;

    public void Text(Object s) {
    	if(c == null) {
    		c = new BitmapText(guiFont, false);
    		c.setLocalTranslation(0, getCamera().getHeight(), 0);
    		c.setSize(guiFont.getCharSet().getRenderedSize());
    		guiNode.attachChild(c);
    	}
        c.setText("" + s); 
    }
    
    private WorldCollision world_collision = new WorldCollision();
    
    private NodeCollision getWorldIntersection() {
        Vector3f origin = cam.getWorldCoordinates(new Vector2f(settings.getWidth() / 2, settings.getHeight() / 2), 0.0f);
        Vector3f direction = cam.getWorldCoordinates(new Vector2f(settings.getWidth() / 2, settings.getHeight() / 2), 0.3f);
        direction.subtractLocal(origin).normalizeLocal();

        Ray ray = new Ray(origin, direction);
        for(int i = 0; i < node_collision.size(); i++) {
	        CollisionResults results = new CollisionResults();
	        int numCollisions = node_collision.get(i).node.collideWith(ray, results);
	        if (numCollisions > 0) {
	            CollisionResult hit = results.getClosestCollision();
	            node_collision.get(i).collide(this, hit);
	            return node_collision.get(i);
	        }
        }
        CollisionResults results = new CollisionResults();
        int numCollisions = rootNode.collideWith(ray, results);
        if (numCollisions > 0) {
            CollisionResult hit = results.getClosestCollision();
            if(hit.getGeometry().getName().equals("Level")) {
            	world_collision.collide(this, hit);
            	return world_collision;
            }
        }
        return null;
    }
}
