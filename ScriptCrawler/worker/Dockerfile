FROM node:latest
WORKDIR "/app"
COPY ./package.json ./
RUN npm install
# copy index.js, intersection.js etc
COPY . .
CMD ["npm", "run", "start"]