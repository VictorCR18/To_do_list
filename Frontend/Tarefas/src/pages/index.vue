<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" md="8">
        <v-card class="pa-4" elevation="2">
          <v-card-title>
            <h1 class="text-h5">Minhas Tarefas</h1>
          </v-card-title>
          <v-card-text> <TarefaList /> </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import TarefaList from "@/components/TarefaList.vue";
</script>
