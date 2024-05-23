import javax.swing.JFileChooser

import com.neuronrobotics.bowlerstudio.vitamins.Vitamins

import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cylinder

// code here

JFileChooser f = new JFileChooser(new File(System.getProperty("user.home")+"/Downloads/all/"));
f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

f.showSaveDialog(null);

double scale = 1.0/3.5

def dir = f.getSelectedFile()
System.out.println(dir);

File[] listOfFiles = dir.listFiles();

ArrayList<CSG> stls= []
CSG cutout = new Cylinder(4, 8).toCSG()
for(int i=0;i<listOfFiles.length;i++) {
	
	File file=listOfFiles[i];
	println "Loading "+file
	CSG get = Vitamins.get(file)
	 
	get=get.scale(scale)
	get=get.difference(cutout.move(get.getCenter()).toZMin())
	get.setName(file.getName())
	stls.add(get)
}
println "Loaded houses # "+stls.size()

return stls