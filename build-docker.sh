#!/bin/bash

# Hospital Management System - Docker Build Script

echo "🏥 Building Hospital Management System Docker Image..."

# Stop and remove existing containers
echo "🛑 Stopping existing containers..."
docker-compose down

# Remove old images to force rebuild
echo "🗑️  Removing old images..."
docker rmi smart-clinic-backend:latest 2>/dev/null || true
docker rmi java-database-capstone-hospital-app:latest 2>/dev/null || true

# Build new image
echo "🔨 Building new Docker image..."
docker-compose build --no-cache

# Start the services
echo "🚀 Starting services..."
docker-compose up -d

echo "✅ Build complete!"
echo ""
echo "🌐 Application will be available at:"
echo "   - Hospital App: http://localhost:8080"
echo "   - phpMyAdmin:   http://localhost:8081"
echo ""
echo "📊 Check logs with: docker-compose logs -f hospital-app"
echo "🛑 Stop services with: docker-compose down"