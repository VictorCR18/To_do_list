<template>
    <div class="text-center pa-10">
      <v-container>
        <v-row justify="center">
          <v-col cols="12" md="8">
            <h1 class="text-h2 font-weight-bold mb-4">404</h1>
            <p class="text-h5 mb-6">Página não encontrada 😢</p>
            <v-btn color="primary" @click="$router.push('/')">
              Voltar para o início
            </v-btn>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </template>
  