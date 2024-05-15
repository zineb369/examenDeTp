from flask import Flask, request, jsonify
from flask_mysqldb import MySQL

app = Flask(__name__)

# Configuration de la base de données
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = ''
app.config['MYSQL_DB'] = 'projetsBD'
mysql = MySQL(app)

# Endpoint pour ajouter un projet
@app.route('/projet', methods=['POST'])
def ajouter_projet():
    data = request.get_json()
    nom = data['nom']
    image = data['image']
    description = data['description']
    status = data['status']

    cur = mysql.connection.cursor()
    cur.execute("INSERT INTO Projet (nom, image, description, status) VALUES (%s, %s, %s, %s)", (nom, image, description, status))
    mysql.connection.commit()
    cur.close()

    return jsonify({"message": "Projet ajouté avec succès"}), 201

# Endpoint pour récupérer tous les projets
@app.route('/projets', methods=['GET'])
def get_projets():
    cur = mysql.connection.cursor()
    cur.execute("SELECT * FROM Projet")
    result = cur.fetchall()
    cur.close()

    projets = []
    for projet in result:
        projet_data = {
            'id': projet[0],
            'nom': projet[1],
            'image': projet[2],
            'description': projet[3],
            'status': projet[4]
        }
        projets.append(projet_data)

    return jsonify(projets)

# Endpoint pour mettre à jour un projet
@app.route('/projet/<int:id>', methods=['PUT'])
def modifier_projet(id):
    data = request.get_json()
    nom = data['nom']
    image = data['image']
    description = data['description']
    status = data['status']

    cur = mysql.connection.cursor()
    cur.execute("UPDATE Projet SET nom=%s, image=%s, description=%s, status=%s WHERE id=%s", (nom, image, description, status, id))
    mysql.connection.commit()
    cur.close()

    return jsonify({"message": "Projet modifié avec succès"})

# Endpoint pour supprimer un projet
@app.route('/projet/<int:id>', methods=['DELETE'])
def supprimer_projet(id):
    cur = mysql.connection.cursor()
    cur.execute("DELETE FROM Projet WHERE id = %s", (id,))
    mysql.connection.commit()
    cur.close()

    return jsonify({"message": "Projet supprimé avec succès"})

if __name__ == '__main__':
    app.run(debug=True)
