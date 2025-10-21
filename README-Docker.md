# 🏥 Hospital Management System - Docker Guide

## 🚀 Quick Start

### Prerequisites
- Docker Desktop installed and running
- Docker Compose (included with Docker Desktop)

### 🔧 Build and Run

#### Option 1: Using Docker Compose (Recommended)
```bash
# Build and start all services
docker-compose up --build -d

# View logs
docker-compose logs -f hospital-app

# Stop services
docker-compose down
```

#### Option 2: Using Build Scripts
**Windows:**
```cmd
build-docker.bat
```

**Linux/Mac:**
```bash
chmod +x build-docker.sh
./build-docker.sh
```

#### Option 3: Manual Docker Commands
```bash
# Build the image
docker build -t hospital-management-app ./app

# Run with database
docker-compose up -d mysql-db
docker run -p 8080:8080 --network java-database-capstone_hospital-network \
  -e SPRING_PROFILES_ACTIVE=docker \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-db:3306/hospital_db \
  hospital-management-app
```

## 🌐 Access Points

Once running, access the application at:

- **Hospital Management System**: http://localhost:8080
- **API Health Check**: http://localhost:8080/actuator/health
- **Database Admin (phpMyAdmin)**: http://localhost:8081

## 🎯 Login Credentials

### Demo Accounts
- **Admin**: `admin` / `admin123`
- **Doctor**: `doctor` / `doctor123`
- **Patient**: `patient` / `patient123`

### Database Access
- **Username**: `hospital_user`
- **Password**: `hospital_pass`
- **Database**: `hospital_db`

## 📊 Monitoring

### Health Checks
```bash
# Check application health
curl http://localhost:8080/actuator/health

# View detailed metrics
curl http://localhost:8080/actuator/metrics
```

### Container Status
```bash
# View running containers
docker-compose ps

# View logs
docker-compose logs hospital-app
docker-compose logs mysql-db

# Monitor resource usage
docker stats
```

## 🛠️ Development

### Rebuild After Code Changes
```bash
# Rebuild and restart
docker-compose down
docker-compose up --build -d
```

### Database Management
```bash
# Connect to MySQL container
docker exec -it hospital-mysql-db mysql -u hospital_user -p

# Backup database
docker exec hospital-mysql-db mysqldump -u hospital_user -p hospital_db > backup.sql

# Restore database
docker exec -i hospital-mysql-db mysql -u hospital_user -p hospital_db < backup.sql
```

## 🔧 Configuration

### Environment Variables
The application supports these environment variables:

- `SPRING_PROFILES_ACTIVE`: Active Spring profile (default: docker)
- `SPRING_DATASOURCE_URL`: Database connection URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password
- `JAVA_OPTS`: JVM options for performance tuning

### Custom Configuration
Edit `app/src/main/resources/application-docker.properties` for Docker-specific settings.

## 🐛 Troubleshooting

### Common Issues

**Port Already in Use:**
```bash
# Check what's using port 8080
netstat -ano | findstr :8080
# Stop the process or change the port in docker-compose.yml
```

**Database Connection Issues:**
```bash
# Check database container
docker-compose logs mysql-db
# Ensure the database is healthy
docker-compose ps
```

**Application Won't Start:**
```bash
# Check application logs
docker-compose logs hospital-app
# Verify Java version and dependencies
```

### Clean Rebuild
```bash
# Remove all containers and images
docker-compose down
docker system prune -f
docker-compose up --build --force-recreate
```

## 📁 Project Structure

```
📦 java-database-capstone/
├── 📂 app/                          # Spring Boot application
│   ├── 📄 Dockerfile               # Application container definition
│   ├── 📄 .dockerignore            # Docker build exclusions
│   └── 📂 src/main/resources/
│       └── 📄 application-docker.properties
├── 📄 docker-compose.yml           # Multi-service orchestration
├── 📄 build-docker.bat            # Windows build script
├── 📄 build-docker.sh             # Linux/Mac build script
└── 📄 README-Docker.md            # This file
```

## 🔒 Security Notes

- The application runs as a non-root user inside the container
- Database credentials should be changed for production
- Health checks are enabled for container orchestration
- Resource limits are configured for stable operation

## 🚀 Production Deployment

For production deployment:

1. Update database credentials in `docker-compose.yml`
2. Configure proper SSL certificates
3. Set up container orchestration (Kubernetes, Docker Swarm)
4. Implement proper logging and monitoring
5. Configure backup strategies

---

**Need help?** Check the logs with `docker-compose logs -f hospital-app` for detailed error information.