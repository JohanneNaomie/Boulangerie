    #!/bin/bash

# Variables (Adjust these paths as needed)
PROJECT_NAME="BAKERY_FINAL"
TOMCAT_HOME="C:\Program Files\Apache Software Foundation\Tomcat 10.1"  # Tomcat installation path
PROJECT_SRC="C:\Users\Asus\Documents\BAKERY"
WEBAPP_DIR="${TOMCAT_HOME}/webapps/${PROJECT_NAME}"
LIB_DIR="${PROJECT_SRC}/lib"    # Location of external .jar files
CLASS_DIR="${PROJECT_SRC}/class"  # Compiled .class files location
TOMCAT_BIN="C:\xampp\tomcat\bin"

# Clean any previous deployment
rm -rf "$WEBAPP_DIR"
mkdir -p "$WEBAPP_DIR/WEB-INF/classes"
mkdir -p "$WEBAPP_DIR/WEB-INF/lib"

# Copy compiled classes to WEB-INF/classes
cp -R "$CLASS_DIR"/* "$WEBAPP_DIR/WEB-INF/classes/"

# Copy .jar files to lib directory
cp "$LIB_DIR"/*.jar "$WEBAPP_DIR/WEB-INF/lib/"

# Copy JSP, HTML, and other resources
cp "$PROJECT_SRC/index.jsp" "$WEBAPP_DIR"  # Adjust if index.jsp is in another location

cp -r "$PROJECT_SRC/assets" "$WEBAPP_DIR"
 
cp  -r "$PROJECT_SRC/views" "$WEBAPP_DIR/WEB-INF"  # Adjust if index.jsp is in another location

# Copy web.xml
cp "$PROJECT_SRC/WEB-INF/web.xml" "$WEBAPP_DIR/WEB-INF/"

#restart tomcat

echo "Deployment complete! Access the application at http://localhost:8080/${PROJECT_NAME}"


# export CATALINA_HOME="C:/Program Files/Apache Software Foundation/Tomcat 10.1"


# # Restart Tomcat using cmd to run Windows .bat files
# cmd //c "${TOMCAT_BIN}/shutdown.bat"
# cmd //c "${TOMCAT_BIN}/startup.bat"


# echo "Tomcat sutdown and started"
