# Etapa de construcción
FROM node:18 as build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

# Etapa de ejecución
FROM nginx:alpine
COPY --from=build /app/dist/crud-paginacion-springboot /usr/share/nginx/html
EXPOSE 80