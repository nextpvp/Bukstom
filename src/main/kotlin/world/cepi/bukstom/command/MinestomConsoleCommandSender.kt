package world.cepi.bukstom.command

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.minestom.server.MinecraftServer
import org.bukkit.Server
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.conversations.Conversation
import org.bukkit.conversations.ConversationAbandonedEvent
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionAttachment
import org.bukkit.permissions.PermissionAttachmentInfo
import org.bukkit.plugin.Plugin
import world.cepi.bukstom.MinestomServer

class MinestomConsoleCommandSender(val internalServer: MinestomServer) : ConsoleCommandSender {
	override fun sendMessage(message: String) {
		MinecraftServer.getCommandManager().consoleSender.sendMessage(
			PlainTextComponentSerializer.plainText().deserialize(message)
		)
	}

	override fun sendMessage(messages: Array<out String>) {
		for (message in messages) {
			sendMessage(message)
		}
	}

	override fun isOp() = true

	override fun setOp(value: Boolean) {
		// Can't set OP of console
	}

	override fun isPermissionSet(name: String) = true

	override fun isPermissionSet(perm: Permission) = true

	override fun hasPermission(name: String) = true

	override fun hasPermission(perm: Permission) = true

	override fun addAttachment(plugin: Plugin, name: String, value: Boolean): PermissionAttachment {
		TODO("Not yet implemented")
	}

	override fun addAttachment(plugin: Plugin): PermissionAttachment {
		TODO("Not yet implemented")
	}

	override fun addAttachment(plugin: Plugin, name: String, value: Boolean, ticks: Int): PermissionAttachment? {
		TODO("Not yet implemented")
	}

	override fun addAttachment(plugin: Plugin, ticks: Int): PermissionAttachment? {
		TODO("Not yet implemented")
	}

	override fun removeAttachment(attachment: PermissionAttachment) {
		TODO("Not yet implemented")
	}

	override fun recalculatePermissions() {
		TODO("Not yet implemented")
	}

	override fun getEffectivePermissions(): MutableSet<PermissionAttachmentInfo> {
		TODO("Not yet implemented")
	}

	override fun getServer(): Server {
		return internalServer
	}

	override fun getName(): String = "Console"

	override fun isConversing(): Boolean {
		TODO("Not yet implemented")
	}

	override fun acceptConversationInput(input: String) {
		TODO("Not yet implemented")
	}

	override fun beginConversation(conversation: Conversation): Boolean {
		TODO("Not yet implemented")
	}

	override fun abandonConversation(conversation: Conversation) {
		TODO("Not yet implemented")
	}

	override fun abandonConversation(conversation: Conversation, details: ConversationAbandonedEvent) {
		TODO("Not yet implemented")
	}

	override fun sendRawMessage(message: String) =
		MinecraftServer.getCommandManager().consoleSender.sendMessage(message)
}