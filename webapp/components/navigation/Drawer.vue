<template>
    <v-navigation-drawer v-model="drawer" :rail="true" permanent :expand-on-hover="true">
        <v-list density="compact" nav>
            <v-list-item prepend-icon="mdi-home-city" title="Home" value="home" to="/" />
        </v-list>
    </v-navigation-drawer>
</template>

<script lang="ts" setup>
const drawer = ref(true);
</script>
