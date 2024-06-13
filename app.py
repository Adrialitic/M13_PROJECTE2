from flask import Flask, request, jsonify

app = Flask(__name__)

users = {}  # Simple in-memory user store

@app.route('/register', methods=['POST'])
def register():
    data = request.get_json()
    username = data.get('username')
    password = data.get('password')
    email = data.get('email')
    if username in users:
        return jsonify({"error": "User already exists"}), 400
    users[username] = {"password": password, "email": email}
    print(f"User registered: {username}, {email}")
    return jsonify({"token": "fake-jwt-token-for-" + username}), 200

@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    username = data.get('username')
    password = data.get('password')
    user = users.get(username)
    print(f"Login attempt for user: {username}")
    if user and user['password'] == password:
        print(f"Login successful for user: {username}")
        return jsonify({"token": "fake-jwt-token-for-" + username}), 200
    print(f"Login failed for user: {username}")
    return jsonify({"error": "Invalid credentials"}), 401

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8000)
