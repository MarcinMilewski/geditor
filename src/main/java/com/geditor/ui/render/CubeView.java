package com.geditor.ui.render;

import com.geditor.ui.render.model.Cube;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CubeView extends Frame implements ActionListener {

    protected Canvas3D myCanvas3D;
    protected Button myButton = new Button("Exit");

    protected BranchGroup buildViewBranch(Canvas3D c) {
        BranchGroup viewBranch = new BranchGroup();
        Transform3D viewXfm = new Transform3D();
        viewXfm.set(new Vector3f(0.0f, 0.0f, 5.0f));
        TransformGroup viewXfmGroup = new TransformGroup(viewXfm);
        ViewPlatform myViewPlatform = new ViewPlatform();
        PhysicalBody myBody = new PhysicalBody();
        PhysicalEnvironment myEnvironment = new PhysicalEnvironment();
        viewXfmGroup.addChild(myViewPlatform);
        viewBranch.addChild(viewXfmGroup);
        View myView = new View();
        myView.addCanvas3D(c);
        myView.attachViewPlatform(myViewPlatform);
        myView.setPhysicalBody(myBody);
        myView.setPhysicalEnvironment(myEnvironment);
        return viewBranch;
    }


    protected BranchGroup buildContentBranch() {
        BranchGroup contentBranch = new BranchGroup();
        Transform3D rotateCube = new Transform3D();
        rotateCube.set(new AxisAngle4d(1.0, 1.0, 0.0, Math.PI / 4.0));
        TransformGroup rotationGroup = new TransformGroup(rotateCube);
        contentBranch.addChild(rotationGroup);
        Appearance appearance = new Appearance();
//        rotationGroup.addChild(new Cone(1.0f, 2.0f, Cone.GENERATE_NORMALS, appearance));
        Cube cube = new Cube(1, new Color3f(0,1,0));
        rotationGroup.addChild(cube);

        // lights
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 10.0),
                1000.0);
        Color3f light1Color = new Color3f(.7f, .7f, .7f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        rotationGroup.addChild(light1);
        Color3f ambientColor = new Color3f(.4f, .4f, .4f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        rotationGroup.addChild(ambientLightNode);
        return contentBranch;
    }

    public CubeView() {
        myCanvas3D = createCanvas();
        VirtualUniverse myUniverse = new VirtualUniverse();
        Locale myLocale = new Locale(myUniverse);
        myLocale.addBranchGraph(buildViewBranch(myCanvas3D));
        myLocale.addBranchGraph(buildContentBranch());
        setTitle("SimpleWorld");
        setSize(400, 400);
        setLayout(new BorderLayout());
        add("Center", myCanvas3D);
        add("South", myButton);
        myButton.addActionListener(e -> dispose());
        setVisible(true);
    }

    public Canvas3D createCanvas() {
        GraphicsConfiguration config =
                SimpleUniverse.getPreferredConfiguration();
        return new Canvas3D(config);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}