<template>
    <v-dialog v-model="mostrar" max-width="400">
        <v-card>
            <v-card-title class="text-h6">Tem certeza?</v-card-title>
            <v-card-text>Você realmente deseja excluir esta tarefa?</v-card-text>
            <v-card-actions class="justify-end">
                <v-btn color="grey" variant="text" @click="cancelar">Cancelar</v-btn>
                <v-btn color="red" variant="tonal" @click="confirmar">Excluir</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script lang="ts" setup>
import { computed } from 'vue';

const props = defineProps<{
    modelValue: boolean
}>()

const emit = defineEmits<{
    (e: 'update:modelValue', valor: boolean): void
    (e: 'confirmar'): void
}>()

const mostrar = computed({
    get: () => props.modelValue,
    set: (valor) => emit('update:modelValue', valor),
})

const cancelar = () => (mostrar.value = false)
const confirmar = () => {
    emit('confirmar')
    mostrar.value = false
}
</script>