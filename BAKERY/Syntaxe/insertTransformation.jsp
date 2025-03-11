<%@ page import="java.util.List" %>
<%@ page import="materials.Block" %>
<%@ page import="materials.Usuel" %>
<%@ page import="connexion.Connexion" %>
<%@ page import="java.sql.Connection" %>

<%

    List<Block> blocks = request.getAttribute("blocks");
    List<Usuel> usuelBlocks =  request.getAttribute("usuelBlocks");

%>

<!DOCTYPE html>
<html>
<head>
    <title>Insert Transformation</title>
</head>
<body>
    <h2>Insert Transformation</h2>

    <form action="processInsertTransformation" method="post">
        <!-- Block Selection -->
        <label for="block">Select Block:</label>
        <select name="blockId" id="block">
            <% for (Block block : blocks) { %>
                <option value="<%= block.getIdBlock() %>">
                    <%= block.getName() %> (Volume: <%= block.getLongeur() * block.getLargeur() * block.getHauteur() %>)
                </option>
            <% } %>
        </select>
        <br><br>

        <!-- Usuel Blocks Quantities -->
        <h3>Usuel Blocks</h3>
        <% for (Usuel usuel : usuelBlocks) { %>
            <label><%= usuel.getName() %> (Volume: <%= usuel.getLongeur() * usuel.getLargeur() * usuel.getHauteur() %>): </label>
            <input type="number" name="usuelQuantity_<%= usuel.getIdKidoroU() %>" min="0" value="0">
            <br>
        <% } %>
        <br>

        <!-- Reste Dimensions -->
        <h3>Reste Dimensions</h3>
        <label for="resteLongeur">Longeur:</label>
        <input type="number" name="resteLongeur" id="resteLongeur" step="0.01" required>
        <br>
        <label for="resteLargeur">Largeur:</label>
        <input type="number" name="resteLargeur" id="resteLargeur" step="0.01" required>
        <br>
        <label for="resteHauteur">Hauteur:</label>
        <input type="number" name="resteHauteur" id="resteHauteur" step="0.01" required>
        <br><br>

        <!-- Submit Button -->
        <button type="submit">Submit Transformation</button>
    </form>
</body>
</html>
